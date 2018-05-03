//
// Created by Leonardo Schlüter on 19/04/18.
// Copyright (c) 2018 Bridge. All rights reserved.
//

import Foundation
import UIKit

extension UIImageView {

    func downloadImageFrom(link: String, contentMode: UIViewContentMode) {
        URLSession.shared.dataTask( with: NSURL(string:link)! as URL, completionHandler: {
            (data, response, error) -> Void in
            DispatchQueue.main.async {
                self.contentMode =  contentMode
                if let data = data { self.image = UIImage(data: data) }
            }
        }).resume()
    }

}