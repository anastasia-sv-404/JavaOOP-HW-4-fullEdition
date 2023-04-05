import java.util.Iterator;
import java.util.LinkedList;

public class TaskManager {
    public LinkedList<Task> commonList;

    public TaskManager() {
        this.commonList = new LinkedList<>();
    }

    public LinkedList<Task> getCommonList() {
        return commonList;
    }

    public void addTask(Task task) {
        if (!this.commonList.contains(task) && task != null) {
            this.commonList.add(task);
        }
    }

    public void removeTask(Task task) {
        Iterator<Task> iterator = this.commonList.iterator();
        while (iterator.hasNext()) {
            Task taskTemp = iterator.next();
            if (taskTemp.equals(task)) {
                iterator.remove();
            }
        }
    }

    public boolean isEmpty() {
        if (this.commonList.size() == 0) {
            return true;
        }
        return false;
    }

    public Task searchTaskById(int id) {
        Task task = null;
        Iterator<Task> iterator = this.commonList.iterator();
        while (iterator.hasNext()) {
            Task taskTemp = iterator.next();
            if (taskTemp.getId() == id) {
                task = taskTemp;
            }
        }
        return task;
    }

    public LinkedList<Task> searchTaskByDescription(String text) {
        LinkedList<Task> list = new LinkedList<>();
        Iterator<Task> iterator = this.commonList.iterator();
        while (iterator.hasNext()) {
            Task taskTemp = iterator.next();
            if (taskTemp.getDescription().contains(text)) {
                list.add(taskTemp);
            }
        }
        return list;
    }

    public LinkedList<Task> getListOfTasks(String type, String state) {
        LinkedList<Task> listOfTasks = new LinkedList<>();
        for (Task task : this.commonList) {
            String taskState = String.valueOf(task.getState());
            if (task.getClass().getSimpleName().equals(type) && taskState.equals(state)) {
                listOfTasks.add(task);
            }
        }
        return listOfTasks;
    }

    public void print() {
        for (Task task : this.commonList) {
            task.print();
        }
    }
}
