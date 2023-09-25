package org.zerock.w1.todo.dto;

import java.time.LocalDate;

public class TodoDTO {
    private  Long tno;
    private  String title;
    private LocalDate dueDate;
    private boolean finished;

    public Long getTno() {
        return tno;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setTno(Long tno) {
        this.tno = tno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return  "TodoDto{"+
                "tno="+tno+
                ",title='"+title+'\''+
                ",dueDate="+dueDate+
                ",finished="+finished+
                '}';
    }
}
