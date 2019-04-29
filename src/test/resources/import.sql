--STUDENTS
INSERT INTO students (first_name, gender, last_name, middle_name, date_of_birth, other_student_details)VALUES ('laura', 'F', 'laura', 'laura', '1990-05-05', 'oth');
INSERT INTO students (first_name, gender, last_name, middle_name, date_of_birth, other_student_details)VALUES ('joaquin', 'M', 'laura', 'laura', '1990-05-05', 'oth');
INSERT INTO students (first_name, gender, last_name, middle_name, date_of_birth, other_student_details)VALUES ('ricky', 'F', 'toso', 'laura', '1990-05-05', 'oth');
INSERT INTO students (first_name, gender, last_name, middle_name, date_of_birth, other_student_details)VALUES ('raul', 'M', 'raul', 'raul', '1990-05-05', 'oth');

--PARENTS
INSERT INTO parents (first_name, gender, last_name, middle_name, other_parent_details)VALUES ('papa1', 'F', 'laura', 'laura', 'oth');
INSERT INTO parents (first_name, gender, last_name, middle_name, other_parent_details)VALUES ('no', 'F', 'sofia', 'sofia', 'oth');
INSERT INTO parents (first_name, gender, last_name, middle_name, other_parent_details)VALUES ('esta', 'F', 'rastha', 'laura', 'oth');
INSERT INTO parents (first_name, gender, last_name, middle_name, other_parent_details)VALUES ('se fue', 'F', 'laura', 'laura', 'oth');

-- FAMILIES
INSERT INTO families (head_of_family_parent, family_name)VALUES (1, 'intro');
INSERT INTO families (head_of_family_parent, family_name)VALUES (2, 'intro');
INSERT INTO families (head_of_family_parent, family_name)VALUES (1, 'intro');
INSERT INTO families (head_of_family_parent, family_name)VALUES (2, 'intro');

--STUDENTPARENT
INSERT INTO student_parents (parent_id, student_id)VALUES (1, 2);
INSERT INTO student_parents (parent_id, student_id)VALUES (2, 1);

--FamilyMembers
INSERT INTO family_members (family_id, parent_or_student_member, parent_id, student_id)VALUES (1, 'Namee', 1, 1);
INSERT INTO family_members (family_id, parent_or_student_member, parent_id, student_id)VALUES (2, 'Namee', 1, 1);
INSERT INTO family_members (family_id, parent_or_student_member, parent_id, student_id)VALUES (2, 'Namee', 1, 1);
INSERT INTO family_members (family_id, parent_or_student_member, parent_id, student_id)VALUES (1, 'Namee', 1, 1);

