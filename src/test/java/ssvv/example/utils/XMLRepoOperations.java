package ssvv.example.utils;

import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLRepoOperations {
    private static final String gradesRepoFilePath = "fisiere/Note_test.xml";
    private static final String assignmentsRepoFilePath = "fisiere/Teme_test.xml";
    private static final String studentsRepoFilePath = "fisiere/Studenti_test.xml";
    private static StudentXMLRepo studentsRepo;
    private static TemaXMLRepo assignmentsRepo;
    private static  NotaXMLRepo gradesRepo;

    private static List<File> files;

    public static StudentXMLRepo getStudentsRepo() {
        if (studentsRepo == null) {
            createTestRepoFiles();
            reInitRepos();
        }
        return studentsRepo;
    }

    public static TemaXMLRepo getAssignmentsRepo() {
        if (assignmentsRepo == null) {
            createTestRepoFiles();
            reInitRepos();
        }
        return assignmentsRepo;
    }

    public static NotaXMLRepo getGradesRepo() {
        if (gradesRepo == null) {
            createTestRepoFiles();
            reInitRepos();
        }
        return gradesRepo;
    }

    private static void createTestRepoFiles() {
        files = new ArrayList<>();
        files.add(new File(studentsRepoFilePath));
        files.add(new File(assignmentsRepoFilePath));
        files.add(new File(gradesRepoFilePath));

        boolean allFilesCreated = true;

        for (File f: files) {
            try {
                allFilesCreated &= f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            f.deleteOnExit();
        }

        if (!allFilesCreated) {
            throw new RuntimeException("Could not create all XMLRepository files");
        }
    }

    public static void reInitRepos() {
        PrintWriter writer;
        if (files.isEmpty()) {
            createTestRepoFiles();
        }
        for (File f: files) {
            try {
                writer = new PrintWriter(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox/>");
            writer.close();
        }
        studentsRepo = new StudentXMLRepo(studentsRepoFilePath);
        assignmentsRepo = new TemaXMLRepo(assignmentsRepoFilePath);
        gradesRepo = new NotaXMLRepo(gradesRepoFilePath);
    }

}
