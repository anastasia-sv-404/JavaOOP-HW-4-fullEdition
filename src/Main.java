public class Main {
    public static void main(String[] args) {

        TaskManager tm = new TaskManager(); // Условно - модель данных (условно, потому что называется иначе
        // в рамках данного проекта).

        // Чтобы была возможность проверить все возможности программы - можно расскомитить эту часть.
        // Иначе все задачи в рамках данного проекта придется вносить вручную через меню.
//        PersonalTask task = new PersonalTask("Сделать ДЗ", 1, "03.04.2023", "Обучение");
//        WorkTask task2 = new WorkTask("Сделать работу", 1, "27.03.2023", "начальник");
//        PersonalTask task3 = new PersonalTask("Убрать", 3, "03.04.2023", "Дом");
//        WorkTask task4 = new WorkTask("Сделать работу2", 4, "28.03.2023", "другой начальник");
//        tm.addTask(task);
//        tm.addTask(task2);
//        tm.addTask(task3);
//        tm.addTask(task4);

        View view = new View();
        Menu menu = new Menu(view, tm); // Условно - контроллер/презентер.
        menu.startMenu();
    }

}