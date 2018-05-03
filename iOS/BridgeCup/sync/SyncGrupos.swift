//
// Created by Leonardo Schlüter on 18/04/18.
// Copyright (c) 2018 Bridge. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class SyncGrupos {

    var year: Int!
    var listener: OnSyncFinishedListener

    init(year: Int, listener: OnSyncFinishedListener){
        self.year = year
        self.listener = listener
    }

    func sync(){
        Alamofire.request(BridgeCupRestClientUtil.GET_GRUPOS(self.year)).validate().responseData(queue: nil, completionHandler: { response in
            switch response.result {
            case .success:
                guard let jsonString = response.result.value else {
                    print(" Requisição com resposta vazia.")
                    self.listener.onGruposSyncFinished(grupos: [])
                    return
                }

                let decoder = JSONDecoder()
                var grupos: [Grupo] = []
                do {
                    grupos = try decoder.decode([Grupo].self, from: jsonString)
                } catch {
                    print(error)
                }
                self.listener.onGruposSyncFinished(grupos: grupos)
                return

                print(jsonString)
            case .failure(let error):
                print(error)
                return ()

            }
        })

    }
}

protocol OnSyncFinishedListener {
    func onGruposSyncFinished(grupos: [Grupo])
}