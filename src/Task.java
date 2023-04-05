import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

//Iterator<String> - обучение применению на практике информации из Лекции 3 - перебор полей класса.
//Используется далее для вывода информации в cvs-файл - каждое поле в разном столбце.
public abstract class Task implements  Iterator<String>, Comparable<Task> {
    private int id;
    protected String description;
    protected int priority;
    protected LocalDateTime createDT;
    protected LocalDateTime deadLineDate;
    protected LocalDateTime completedDT;
    private DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    protected State state;
    private static int defaultIndex;
    private int index;

    static {
        defaultIndex = 1;
    }

    public Task(String description, int priority, String deadLineDate) {
        this.id = defaultIndex++;
        this.description = description;
        if (priority < 0 || priority > 10) {
            this.priority = 10;
        } else {
            this.priority = priority;
        }
        this.createDT = LocalDateTime.now();
        this.deadLineDate = LocalDateTime.parse(deadLineDate + " 23:59", formatDT);
        this.state = State.actual;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getCreateDT() {
        return createDT;
    }

    public String getCreateDTString() {
        return createDT.format(formatDT);
    }

    public LocalDateTime getDeadLineDate() {
        return deadLineDate;
    }

    public String getDeadLineDateString() {
        return deadLineDate.format(formatDate);
    }

    public LocalDateTime getCompletedDT() {
        return completedDT;
    }

    public String getCompletedDTString() {
        if (this.completedDT != null) {
            return completedDT.format(formatDT);
        } else {
            return "не заполнено";
        }
    }

    public State getState() {
        return state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDeadLineDate(String deadLineDate) {
        this.deadLineDate = LocalDateTime.parse(deadLineDate + " 23:59", formatDT);
    }

    private void setCompletedDT() {
        this.completedDT = LocalDateTime.now();
    }

    private void setState(State state) {
        this.state = state;
    }

    public void completeTask() {
        if (State.actual.equals(this.getState())) {
            setState(State.completed);
            setCompletedDT();
        } else {
            System.out.println("Задача уже была выполнена.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return task.id == this.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d Задача: %s, приоритет: %d, дата создания: %s, дедлайн: %s, статус: %s, " +
                        "дата выполнения: %s.", this.id, this.description, this.priority, getCreateDTString(),
                getDeadLineDateString(), getState(), getCompletedDTString());
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public int compareTo(Task o) {
        if (this.priority == o.getPriority()) {
            return this.deadLineDate.compareTo(o.getDeadLineDate());
        } else {
            return this.getPriority() - o.getPriority();
        }
    }

    @Override
    public String next() {
        switch (index) {
            case 1:
                return String.format("%s", this.id);
            case 2:
                return this.description;
            case 3:
                return String.format("%d", this.priority);
            case 4:
                return String.format("%s", getCreateDTString());
            case 5:
                return String.format("%s", getDeadLineDateString());
            case 6:
                return String.format("%s", this.state);
            default:
                return String.format("%s", getCompletedDTString());
        }
    }

    @Override
    public boolean hasNext() {
        return this.index++ < 7;
    }

}
