import java.util.Iterator;

//Iterator<String> - обучение применению на практике информации из Лекции 3 - перебор полей класса.
//Используется далее для вывода информации в cvs-файл - каждое поле в разном столбце.
public class WorkTask extends Task implements Iterator<String> {
    protected String initiator;

    private int index;

    public WorkTask(String description, int priority, String deadLineDate, String initiator) {
        super(description, priority, deadLineDate);
        this.initiator = initiator;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @Override
    public String toString() {
        return String.format("%d Задача: %s, приоритет: %d, дата создания: %s, дедлайн: %s, статус: %s, " +
                        "дата выполнения: %s, инициатор: %s.", getId(), super.description, super.priority,
                getCreateDTString(), getDeadLineDateString(), getState(), getCompletedDTString(), this.initiator);
    }

    @Override
    public String next() {
        switch(this.index){
            case 1:
                return String.format("%s", getId());
            case 2:
                return super.description;
            case 3:
                return String.format("%d", super.priority);
            case 4:
                return String.format("%s", getCreateDTString());
            case 5:
                return String.format("%s", getDeadLineDateString());
            case 6:
                return String.format("%s", super.state);
            case 7:
                return String.format("%s", getCompletedDTString());
            default:
                return String.format("%s", this.initiator);
        }
    }

    @Override
    public boolean hasNext() {
        return this.index++ < 8;
    }

}
