//
// Created by Leonardo Schlüter on 18/04/18.
// Copyright (c) 2018 Bridge. All rights reserved.
//

import Foundation
import Alamofire

enum  BridgeCupRestClientUtil: URLRequestConvertible {


    case GET_GRUPOS(Int) // #2 --  /api/v1/edicoes/{ano}/grupos

    // usado para definir o tipo da requisicao, default: get
    func asURLRequest() throws -> URLRequest {
        var method: HTTPMethod {
            switch self {
            default:
                return .get
            }
        }

        // usado para montar array de parametros do body da URL
        var params: ([String: Any]?) = try {
            switch self {
            default:
                return nil
            }
        }()

        // usado apra adicionar parametros à URL
        let url: URL = {
            var relativePath: String = ""
            switch self {
            case .GET_GRUPOS(let year):
                relativePath = "api/v1/edicoes/\(year)/grupos"
            }

            return URL(string: getServerBasePath() + relativePath)!
        }()

        // monta a URL
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = method.rawValue

        // codifica a requisicao de acordo com o tipo da requisicao
        let encoding: ParameterEncoding = {
            switch method {
            case .get:
                return URLEncoding.default
            default:
                return JSONEncoding.default
            }
        }()

        return try encoding.encode(urlRequest, with: params)
    }

    private func getServerBasePath() -> String {
        return  "https://bridge-worldcup.herokuapp.com/"
    }
}
