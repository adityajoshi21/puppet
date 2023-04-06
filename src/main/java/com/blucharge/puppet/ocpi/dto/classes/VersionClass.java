package com.blucharge.puppet.ocpi.dto.classes;

import lombok.Data;
import lombok.NonNull;

import javax.swing.undo.AbstractUndoableEdit;

@Data
public class VersionClass {
    //@NonNull
    private String version;
    //@NonNull
    private String url;
}
