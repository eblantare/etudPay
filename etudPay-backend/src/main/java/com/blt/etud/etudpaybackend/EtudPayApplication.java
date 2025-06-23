package com.blt.etud.etudpaybackend;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blt.etud.etudpaybackend.entities.PayementStatut;
import com.blt.etud.etudpaybackend.entities.PayementType;
import com.blt.etud.etudpaybackend.entities.Payements;
import com.blt.etud.etudpaybackend.entities.Student;
import com.blt.etud.etudpaybackend.repositories.PayementRepository;
import com.blt.etud.etudpaybackend.repositories.StudentRepository;

@SpringBootApplication
public class EtudPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtudPayApplication.class, args);
	}
	//creation d'étudiants

//@Bean
//CommandLineRunner commandLineRunner(StudentRepository studentRepository, PayementRepository payementRepository) {
//	return args -> {
//		            // Créer un étudiant avec un ID auto
//		            Student student = Student.builder()
//		            		.firstname("Roméo")
//		            		.lastname("ADJIBLI")
//		            		.code("T01")
//		            		.programId("SIA01")
//		            		.build();
//		            studentRepository.save(student);//hibernate gère version ici
//		            //Il faut recharger depuis la base pour que hibernate gère correctement la version
//		            Student studentPersist = studentRepository.findById(student.getId()).orElseThrow();
//
//	        //création de payements pour chaque étudiant
//    		PayementType[]typePay = PayementType.values(); //permet de retourner une liste de valeurs de types ennuméré
//    		Random random = new Random(); // Définition d'un objet aléatoire Random
//	        List<Student> listEtud = studentRepository.findAll();
//	        listEtud.forEach(et->{
//	        	for(int i = 0; i <= 15; i++) {  //boucle pour faire les payements
//                    int index = random.nextInt(typePay.length);// Génération d'un index aléatoire, un int entre 0 et le maximum
//	        		Payements pay = Payements.builder()//Définition des attributs
//	        				.amount(5000+(int)(Math.random()*20000))//montant aléatoire
//	        				.type(typePay[index]) // Type aléatoire
//	        				.statut(PayementStatut.CREATED)
//	        				.datePayement(LocalDate.now())
//	        				.student(et)
//	        				.student(studentPersist)
//	        				.build();
//	        		payementRepository.save(pay);
//	        	}
//	        });
//	    	        
//	};
//}
	
//@Bean
	public CommandLineRunner runner(StudentRepository studentRepository,PayementRepository payementRepository) {
	    return args -> {
	        // 1. Insérer un étudiant neuf
	        Student s = Student.builder()
	                .firstname("Ali")
	                .lastname("Ben")
	                .code("A23")
	                .programId("MATH")  
	                .photo("photo.png")
	                .build();

	        studentRepository.save(s); // Hibernate gère version = 0
	        System.out.println(">>> Étudiant inséré avec ID = " + s.getId() + ", version = " + s.getVersion());

	     	   // 2. RECHARGER depuis la base (jamais utiliser l’objet s de nouveau)
	        Student s2 = studentRepository.findById(s.getId())
	                .orElseThrow(() -> new RuntimeException("Non trouvé"));

	        System.out.println(">>> Étudiant rechargé : version = " + s2.getVersion());

	        // 3. Modifier
	        s2.setFirstname("Adotou");
	        s2.setLastname("ALI");
	        s2.setCode("A12");
	        s2.setProgramId("SIM1");
	        s2.setPhoto("vue.jpg");

	        studentRepository.save(s2); // ✅ mise à jour avec version connue
	        System.out.println(">>> Mise à jour OK : nouvelle version = " + s2.getVersion());
	        
	        Student student = studentRepository.findByCode("A12")
	        	    .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
	        Payements pay = Payements.builder()
		    		.amount(5000+(int)(Math.random()*440000))
		    		.datePayement(LocalDate.now())
		    		.type(PayementType.CASH)
		    		.statut(PayementStatut.CREATED)
		    		.file("recu_ali.pdf")
		    		.student(student)
		    		.build();
		    payementRepository.save(pay);
		    
		    Student s1 = studentRepository.findById("uuid-existan")
		    		.orElseThrow(()->new RuntimeException("Etudiant non trouvé"));
		    pay.setStudent(s1);
		    payementRepository.save(pay);
	    };
	    
	}



}
