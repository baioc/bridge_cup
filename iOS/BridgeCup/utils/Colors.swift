//
// Created by Leonardo Schlüter on 19/04/18.
// Copyright (c) 2018 Bridge. All rights reserved.
//
//
//  Colors.swift

import Foundation
import UIKit


struct Colors {
    // pra adicionar novas cores, basta seguir esse exemplo.
    static let title_color:UIColor = UIColor(red: 255/255, green:  255/255, blue:  255/255 , alpha: 1)
    static let title_background:UIColor = UIColor(red: 244/255, green:  67/255, blue:  54/255 , alpha: 1)
}

extension UIColor {

    func green() -> Int? {
        return (rgb()! & 0x0000FF00) >> 8
    }

    func red() -> Int? {
        return (rgb()! & 0x00FF0000) >> 16
    }

    func blue() -> Int? {
        return (rgb()! & 0x000000FF)
    }

    func rgb() -> Int? {
        var fRed : CGFloat = 0
        var fGreen : CGFloat = 0
        var fBlue : CGFloat = 0
        var fAlpha: CGFloat = 0
        if self.getRed(&fRed, green: &fGreen, blue: &fBlue, alpha: &fAlpha) {
            let iRed = Int(fRed * 255.0)
            let iGreen = Int(fGreen * 255.0)
            let iBlue = Int(fBlue * 255.0)
            let iAlpha = Int(fAlpha * 255.0)

            //  (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are blue).
            let rgb = (iAlpha << 24) + (iRed << 16) + (iGreen << 8) + iBlue
            return rgb
        } else {
            // Could not extract RGBA components:
            return nil
        }
    }
}
