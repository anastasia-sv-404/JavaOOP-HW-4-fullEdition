import java.util.Iterator;

//Iterator<String> - обучение применению на практике информации из Лекции 3 - перебор полей класса.
//Используется далее для вывода информации в cvs-файл - каждое поле в разном столбце.
public class PersonalTask extends Task implements Iterator<String> {
    protected String topic;
    private int index;

    public PersonalTask(String description, int priority, String deadLineDate, String topic) {
        super(description, priority, deadLineDate);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return String.format("%d Задача: %s, приоритет: %d, дата создания: %s, дедлайн: %s, статус: %s, " +
                        "дата выполнения: %s, направление задачи: %s.", getId(), super.description, super.priority,
                getCreateDTString(), getDeadLineDateString(), getState(), getCompletedDTString(), this.topic);
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
                return String.format("%s", this.topic);
        }
    }

    @Override
    public boolean hasNext() {
        return this.index++ < 8;
    }

}