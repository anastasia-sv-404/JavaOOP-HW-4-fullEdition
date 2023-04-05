import java.util.Collections;
import java.util.LinkedList;

public class Menu {
    private TaskManager tm;
    private View view;

    public Menu(View view, TaskManager tm) {
        this.view = view;
        this.tm = tm;
    }

    public View getView() {
        return view;
    }

    public TaskManager getTm() {
        return tm;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setTm(TaskManager tm) {
        this.tm = tm;
    }

    public void startMenu(){
        boolean check = true;
        while (check) {
            this.view.insertMenuItem();
            executeMenuItem();
            System.out.println();
            if (this.view.anotherAction() != 1) {
                System.out.println("Спасибо, до свидания!");
                check = false;
            }
        }
    }
    public void executeMenuItem() {
        switch (this.view.getMenuItem()) {
            case 1:
                createTask(); // Добавить новую задачу. В данном случае - без проверок на корректность ввода формата даты.
                break;
            case 2:
                searchTaskCommon(); //Найти задачу (по ID или по части описания)
                break;
            case 3:
                removeTask();//Удалить задачу (по ID)
                break;
            case 4:
                completeTask();//Перенести задачу в выполненные (по ID)
                break;
            case 5:
                getListOfTasks();//Вывести список задач по введенным параметрам (тип задачи и ее статус)
                break;
            case 6:
                if (this.tm.isEmpty()) {
                    System.out.println("В списке нет задач.");
                } else {
                    ExportTasks et = new ExportTasks(this.tm.getCommonList()); //Распечатать список всех задач в планировщике в файл (csv)
                }
                break;
            default:
                if (this.tm.isEmpty()) {
                    System.out.println("В списке нет задач.");
                } else {
                    Collections.sort(this.tm.getCommonList()); // Естественная сортировка по приоритету. Если приоритеты
                    // одинаковые, то дополнительно по дедлайну.
                    this.tm.print();//Вывести список всех задач в планировщике на экран (с сортировкой по приоритету и дедлайну).
                }
        }
    }

    public void createTask() {
        String description = " ";
        int priority = 0;
        String deadLineDate = "03.04.2023";
        String initiator = " ";
        String topic = " ";

        String type = this.view.insertTypeOfTask();

        System.out.println("Описание задачи.");
        description = this.view.checkString();
        System.out.println("Приоритет задачи.");
        priority = this.view.checkNumber();
        System.out.println("Дедлайн задачи. Формат ввода данных: ДД.ММ.ГГГГ");
        deadLineDate = this.view.checkString(); // В данном случае - без проверок на корректность ввода формата даты дедлайна.

        if ("PersonalTask".equals(type)) {
            System.out.println("Направление задачи.");
            topic = this.view.checkString();
            PersonalTask task = new PersonalTask(description, priority, deadLineDate, topic);
            this.tm.addTask(task);
            System.out.println("Задача добавлена в планировщик.");

        } else {
            System.out.println("Инициатор задачи.");
            initiator = this.view.checkString();
            WorkTask taskW = new WorkTask(description, priority, deadLineDate, initiator);
            this.tm.addTask(taskW);
            System.out.println("Задача добавлена в планировщик.");
        }
    }// В данном случае - без проверок на корректность ввода формата даты дедлайна.

    public void searchTaskCommon() {
        int check = this.view.chooseTypeOfSearch();
        if (check == 1) {
            searchTaskID();
        } else {
            System.out.println("Описание или часть описания для поиска.");
            String descSearch = this.view.checkString();
            LinkedList<Task> list = this.tm.searchTaskByDescription(descSearch);
            if (list.isEmpty()) {
                System.out.println("Задач не найдено.");
            } else {
                System.out.println("Найдены следующие задачи.");
                for (Task task : list) {
                    System.out.println(task);
                }
            }
        }
    }

    public Task searchTaskID() {
        Task task = null;
        System.out.println("ID для поиска.");
        int idSearch = this.view.checkNumber();
        if (this.tm.searchTaskById(idSearch) == null) {
            System.out.println("Задач не найдено.");
        } else {
            System.out.println("Найдена следующая задача.");
            task = this.tm.searchTaskById(idSearch);
            System.out.println(task);
        }
        return task;
    }

    public void removeTask() {
        System.out.println("Для удаления задачи необходимо указать ее ID.");
        Task task = searchTaskID();
        if (task != null) {
            this.tm.removeTask(task);
            System.out.println("Задача удалена.");
        }
    }

    public void completeTask() {
        System.out.println("Для переноса задачи в выполненные необходимо указать ее ID.");
        Task task = searchTaskID();
        if (task != null) {
            task.completeTask();
            System.out.println("Задача выполнена.");
        }
    }

    public void getListOfTasks() {
        String type = this.view.insertTypeOfTask();
        String state = this.view.insertStateOfTask();
        System.out.println();
        LinkedList<Task> list = this.tm.getListOfTasks(type, state);
        if (list.isEmpty()) {
            System.out.println("В списке нет задач по заданным параметрам");
        } else {
            System.out.println("Список задач по заданным параметрам. ");
            for (Task task : list) {
                System.out.println(task);
            }
        }
    }
}

