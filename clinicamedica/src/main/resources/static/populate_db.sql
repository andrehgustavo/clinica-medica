INSERT INTO public.specialty(active, description, name)	
VALUES 	(true, 'Especialista em cirurgia nasal', 'Otorrino'),
		(true,'Especialista em transplante capilar.', 'Dermatologista' ),
		(true, 'Uti', 'Cirurgião-geral'),
		(false, 'Cirurgião-geral', 'Cabeça e pescoço'),
		(true, 'Atendimento clínico', 'Pneumologista'),
		(true, 'Especialista em ombro', 'Ortopedista');


INSERT INTO public.doctor(active, birthday, name)
VALUES 	(true, '1986-08-11', 'André'),
		(true,'1987-09-10', 'Milena' ),
		(true, '1986-08-20', 'Marcel'),
		(false, '1983-09-19', 'Carla'),
		(true, '1988-09-26', 'Clara'),
		(true, '1953-06-13', 'Nésio');

INSERT INTO public.doctor_specialty(doctor_id, specialty_id)
	VALUES 	(1, 1),
			(1, 2),
			(1, 3),
			(2, 1),
			(3, 4),
			(3, 6),
			(4, 5),
			(5, 3),
			(6, 5);