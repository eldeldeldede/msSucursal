package cl.duoc.msSucursal.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.msSucursal.model.Ciudad;
import cl.duoc.msSucursal.model.Comuna;
import cl.duoc.msSucursal.model.Direccion;
import cl.duoc.msSucursal.model.Sucursal;
import cl.duoc.msSucursal.repository.CiudadRepository;
import cl.duoc.msSucursal.repository.ComunaRepository;
import cl.duoc.msSucursal.repository.DireccionRepository;
import cl.duoc.msSucursal.repository.SucursalRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(SucursalRepository sucursalRepo,
                                   DireccionRepository direccionRepo,
                                   ComunaRepository comunaRepo,
                                   CiudadRepository ciudadRepo){
                                    return args ->{

                                        if(ciudadRepo.count() > 0){
                                            System.out.println("No se pudo cargar datos, pues ya existían");
                                        }else{
                                            Ciudad ciudad1 = new Ciudad(null, "Santiago");
                                            Ciudad ciudad2 = new Ciudad(null, "Valparaíso");
                                            Ciudad ciudad3 = new Ciudad(null, "Concepción");
                                            ciudadRepo.save(ciudad1);
                                            ciudadRepo.save(ciudad2);
                                            ciudadRepo.save(ciudad3);

                                            Comuna comuna1 = new Comuna(null, "Providencia");
                                            Comuna comuna2 = new Comuna(null, "Viña del Mar");
                                            Comuna comuna3 = new Comuna(null, "Talcahuano");
                                            comunaRepo.save(comuna1);
                                            comunaRepo.save(comuna2);
                                            comunaRepo.save(comuna3);

                                            Direccion direccion1 = new Direccion(null, "Av. Providencia", "1234", ciudad1, comuna1);
                                            Direccion direccion2 = new Direccion(null, "Calle Valparaíso", "5678", ciudad2, comuna2);
                                            Direccion direccion3 = new Direccion(null, "Calle Concepción", "9101", ciudad3, comuna3);
                                            direccionRepo.save(direccion1);
                                            direccionRepo.save(direccion2);
                                            direccionRepo.save(direccion3);

                                            Sucursal sucursal1 = new Sucursal(null, "Sucursal Providencia", "+56212345678", "rentamcqueenprovidencia@email.cl", "Lunes a Viernes 9:00-18:00", direccion1);
                                            Sucursal sucursal2 = new Sucursal(null, "Sucursal Viña del Mar", "+56321234567", "rentamcqueenviniadelmar@email.cl", "Lunes a Viernes 9:00-18:00", direccion2);
                                            Sucursal sucursal3 = new Sucursal(null, "Sucursal Concepción", "+56412345678", "rentamcqueenconcepcion@email.cl", "Lunes a Viernes 9:00-18:00", direccion3);
                                            sucursalRepo.save(sucursal1);
                                            sucursalRepo.save(sucursal2);
                                            sucursalRepo.save(sucursal3);
                                        }
                                    };
                                   }
}
