package tk.tommy.service;

import org.springframework.stereotype.Service;

@Service
public class Cat implements Animal {


    @Override
    public boolean accept(String name) {
        return name.equals("Cat");
    }

    @Override
    public void action() {
        System.out.println("Cat");
    }
}
