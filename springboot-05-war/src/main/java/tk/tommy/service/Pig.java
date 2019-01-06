package tk.tommy.service;

import org.springframework.stereotype.Service;

@Service
public class Pig implements Animal {
    @Override
    public boolean accept(String name) {
        return name.equals("Pig");
    }
    @Override
    public void action() {
        System.out.println("Pig");
    }
}
