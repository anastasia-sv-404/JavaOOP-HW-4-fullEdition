import java.util.Scanner;

public class View {
    private int menuItem;
    public int getMenuItem() {
        return menuItem;
    }

    public void printMenu() {
        System.out.println("\nВведите номер действия, которое вы хотите выполнить:\n" +
                "1 - Добавить новую задачу\n" +
                "2 - Найти задачу (по ID или по части описания)\n" +
                "3 - Удалить задачу (по ID)\n" +
                "4 - Перенести задачу в выполненные (по ID)\n" +
                "5 - Вывести список задач по введенным параметрам (тип задачи и ее статус)\n" +
                "6 - Распечатать список всех задач в планировщике в файл (csv)\n" +
                "7 - Вывести список всех задач в планировщике на экран (с сортировкой по приоритету и дедлайну).");
    }

    public int insertMenuItem() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            printMenu();
            boolean flag = scanner.hasNextInt();
            if (!flag) {
                System.out.println("Вы ввели неверное значение. Попробуйте еще раз.\n");
            } else {
                this.menuItem = scanner.nextInt();
                if (this.menuItem < 1 || this.menuItem > 7) {
                    System.out.println("В меню есть пункты от 1 до 7. Введите верное значение.\n");
                } else {
                    return this.menuItem;
                }
            }
        }
    }
    public String insertTypeOfTask() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите тип задачи: ");
            String type = scanner.nextLine();
            if (String.valueOf(TypesOfTasks.WorkTask).equals(type) || String.valueOf(TypesOfTasks.PersonalTask).equals(type)) {
                return type;
            } else {
                System.out.println("Вводите один из следующих типов: PersonalTask или WorkTask.");
            }
        }
    }

    public String insertStateOfTask() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите статус задачи: ");
            String state = scanner.nextLine();
            if (String.valueOf(State.actual).equals(state) || String.valueOf(State.completed).equals(state)) {
                return state;
            } else {
                System.out.println("Вводите один из следующих типов: actual или completed.");
            }
        }
    }

    public int checkNumber() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите число: ");
            boolean flag = scanner.hasNextInt();
            if (!flag) {
                System.out.println("Вы ввели неверное значение. Попробуйте еще раз.");
            } else {
                int num = scanner.nextInt();
                return num;
            }
        }
    }

    public String checkString() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите текстовое значение: ");
            boolean flag = scanner.hasNextLine();
            if (!flag) {
                System.out.println("Вы ввели неверное значение. Попробуйте еще раз.");
            } else {
                String item = scanner.nextLine();
                return item;
            }
        }
    }

    public int chooseTypeOfSearch() {
        System.out.println("Введите 1 - для поиска задачи по id, введите 2 - для поиска задачи по описанию.");
        int check = checkNumber();
        while (true) {
            if (check == 1 || check == 2) {
                return check;
            } else {
                System.out.println("Есть только 2 варианта поиска. Используйте один из них.");
                check = checkNumber();
            }
        }
    }

    public int anotherAction() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Желаете продолжить? (1 - да, любое другое число - нет): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Вы ввели неверное значение. Попробуйте еще раз.");
            } else {
                int answer = scanner.nextInt();
                return answer;
            }
        }
    }
}
