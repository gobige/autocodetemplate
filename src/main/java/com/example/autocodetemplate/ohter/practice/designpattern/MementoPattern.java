package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 */
public class MementoPattern {
    public static void main(String[] args) {
        ArchiveTool archiveTool = new ArchiveTool();
        ArchiveArea archiveArea = new ArchiveArea();

        archiveTool.setState("三打白骨精");
        archiveTool.setState("大战红孩儿");
        archiveArea.add(archiveTool.saveArchiveFile());
        archiveTool.setState("真假美猴王");
        archiveArea.add(archiveTool.saveArchiveFile());
        archiveTool.setState("三借芭蕉扇");
        archiveArea.add(archiveTool.saveArchiveFile());

        int i = 0;
        ArchiveFile file;
        while (true) {
            file = archiveArea.get(i);
            if (file == null) {
                return;
            }
            System.out.println("存档记录--" + file.getState());
            i++;
        }
    }
}

/**
 * 存档区
 */
class ArchiveArea {
    private List<ArchiveFile> archiveFiles = new ArrayList<>();

    public void add(ArchiveFile file) {
        archiveFiles.add(file);
    }

    public ArchiveFile get(Integer index) {
        if (index < archiveFiles.size()) {
            return archiveFiles.get(index);
        }

        return null;
    }
}

/**
 * 存档工具
 */
class ArchiveTool {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArchiveFile saveArchiveFile() {
        return new ArchiveFile(state);
    }

    public void getArchiveFileState(ArchiveFile archiveFile) {
        state = archiveFile.getState();
    }
}

/**
 * 存档文件
 */
class ArchiveFile {
    private String state;

    ArchiveFile(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}