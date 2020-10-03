package com.example.rxtutor;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Task> createTasksList(){
        /*List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Alex"));
        tasks.add(new Task("Max"));
        tasks.add(new Task("Vlad"));
        tasks.add(new Task("John"));*/
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};
        List<Task> tasks = new ArrayList<>();

        for (String name : maleUsers) {
            Task task = new Task(name);
            task.setName(name);

            tasks.add(task);
        }
        return tasks;
    }

    public static List<Address> createAddressList(){
        List<Address> address = new ArrayList<>();
        address.add(new Address("1600 Amphitheatre Parkway, Mountain View, CA 94043"));
        address.add(new Address("2300 Traverwood Dr. Ann Arbor, MI 48105"));
        address.add(new Address("500 W 2nd St Suite 2900 Austin, TX 78701"));
        address.add(new Address("355 Main Street Cambridge, MA 02142"));
        return address;
    }
}
