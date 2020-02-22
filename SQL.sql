INSERT INTO usuarios (username, password, enabled) VALUES ('camilo','$2a$10$rlg4Gau7iT6IIuiPdeX93OB9zgwf3TOGdDhrctrGKUw00RQX3fAXa',1);
INSERT INTO usuarios (username, password, enabled) VALUES ('admin','$2a$10$JPCaXcD3xH2TSmyUQM8Y0OG9ULfWdby1g36oV67tuyaJNkMSuVQX6',1);   

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);

 select * from usuarios inner join usuarios_roles on usuarios.id = usuarios_roles.Usuario_id inner join roles on usuarios_roles.role_id = roles.id; 

update usuarios set nombre="camilo", apellido = "diaz", email="camilo.diazj@utadeo.edu.co" where id = 1;
update usuarios set nombre="andres", apellido = "jaimes", email="andres.j@utadeo.edu.co" where id = 2;