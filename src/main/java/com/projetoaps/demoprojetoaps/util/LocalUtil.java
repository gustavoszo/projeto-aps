package com.projetoaps.demoprojetoaps.util;

import com.projetoaps.demoprojetoaps.entity.Local;

public class LocalUtil {
    
    public static Local getLocalByPath(String local) {
        for (Local element : Local.values()) {
            if (element.getPathString().equals(local)) {
                return element;
            }
        }
        return null;
    }

}
