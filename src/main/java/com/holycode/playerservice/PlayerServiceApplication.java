package com.holycode.playerservice;

import com.holycode.playerservice.domain.Player;
import com.holycode.playerservice.repository.PlayerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PlayerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApplication.class, args);
    }

}

@Component
class DataLoader implements ApplicationRunner {

    private PlayerRepository playerRepository;

    public DataLoader(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        playerRepository.save(new Player(1L, "Dejan", 1L));
        playerRepository.save(new Player(2L, "Dejan", 2L));
        playerRepository.save(new Player(3L, "Goran", 0L));
        playerRepository.save(new Player(4L, "Marija", 0L));
        playerRepository.save(new Player(5L, "Katarina", 0L));
    }
}