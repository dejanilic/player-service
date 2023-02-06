package com.holycode.playerservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// Za testove koristimo novi profil, kako bismo imali mogucnost da koristimo test bazu, a ne npr. produkcionu.
// Zgodno je recimo napraviti profile za unit i integracione testove odvojeno, jer se integr. mnogo sporije pokrecu.
@ActiveProfiles("test")
public class PlayerServiceApplicationTests {

}
