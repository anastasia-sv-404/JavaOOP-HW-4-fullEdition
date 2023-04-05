import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ExportTasks {
    public ExportTasks(LinkedList<Task> list) {
        StringBuilder sb = new StringBuilder();
        File file = new File("tasks.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task task : list) {
                while(task.hasNext()){
                    sb.append(task.next());
                    sb.append(";");
                }
                sb.append("\n");
            }
            writer.write(sb.toString());
            writer.close();
            System.out.println("Задачи записаны в файл.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
