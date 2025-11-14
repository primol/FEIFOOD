package Controller;

import Model.Aluno;

public class UserSession {
    private static UserSession instance;
    private Aluno loggedInAluno;

    private UserSession() {//tem q ter isso 
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public Aluno getLoggedInAluno() {
        return loggedInAluno;
    }

    public void setLoggedInAluno(Aluno loggedInAluno) {
        this.loggedInAluno = loggedInAluno;
    }

    public void clearSession() {
        loggedInAluno = null;
    }
}
