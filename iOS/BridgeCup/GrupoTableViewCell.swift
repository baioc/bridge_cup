//
// Created by Leonardo Schlüter on 18/04/18.
// Copyright (c) 2018 Bridge. All rights reserved.
//

import UIKit

class GrupoTableViewCell: UITableViewCell {

    
    @IBOutlet weak var icone: UIImageView!
    @IBOutlet weak var grupoLabel: UILabel!

    func setFieldValue(grupo: Grupo){
        icone.downloadImageFrom(link: grupo.selecoes.first!.iconeURL , contentMode: UIViewContentMode.redraw)
        grupoLabel.text = grupo.letra
    }
}
