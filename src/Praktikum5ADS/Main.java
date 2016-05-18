package Praktikum5ADS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	static ArrayList<Student> studentList;
	static AVLTree tree;
	static AVLTree deletedTree;
	static Student tmpSt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		while (true) {
			try {
				printMenue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void init() {

		studentList = new ArrayList<Student>();
		tree = new AVLTree(); // BinaryTree();
		deletedTree = new AVLTree(); // BinaryTree();
		tmpSt = new Student();
		int rnd = ThreadLocalRandom.current().nextInt(15, 50 + 1);
		while (rnd > 0) {
			int rndmatnr = ThreadLocalRandom.current().nextInt(100000, 200000 + 1);
			Student st = new Student(rndmatnr, "Peter", "Petersen");
			addStudent(st);
			rnd--;
		}
	}

	public static BinaryTreeNode genNode(Student st) {
		BinaryTreeNode node = new BinaryTreeNode();
		node.setKey(st.getMatNr());
		return node;
	}

	public static void addStudent(Student st) {
		BinaryTreeNode node = deletedTree.search(genNode(st));
		if (node == null) {
			studentList.add(studentList.size(), st);
			node = genNode(st);
			node.setValue(studentList.size() - 1);
			tree.insert(node);
			//new
			tree.recursiveBalance(tree.getRoot());
		} else {
			studentList.get(node.getValue()).setDeleted(false);
			studentList.get(node.getValue()).setName(st.getName());
			studentList.get(node.getValue()).setAdresse(st.getAdresse());
			tree.insert(node);
			deletedTree.delete(node);
			//new
			tree.recursiveBalance(tree.getRoot());
			deletedTree.recursiveBalance(tree.getRoot());
		}
	}

	public static void deleteStudent(Student st) {
		BinaryTreeNode node = tree.search(genNode(st));
		// BinaryTreeNode node = genNode(st);
		// int index = tree.search(node).getValue();
		if (tree.delete(node)) {
			deletedTree.insert(node);
			studentList.get(node.getValue()).setDeleted(true);
			//new
			tree.recursiveBalance(tree.getRoot());
			deletedTree.recursiveBalance(tree.getRoot());
		} else
			System.out.println("\n\nMatrikelnummer nicht vorhanden, oder kann nicht gelöscht werden.\n\n");
	}

	public static void modifyStudent(Student st) {
		BinaryTreeNode node = tree.search(genNode(st));
		if (node == null) {
			System.out.println("\n\nMatrikelnummer nicht vorhanden, oder kann nicht bearbeitet werden.\n\n");
			return;
		}
		System.out.println("\nStudent bearbeiten:");
		System.out.println("\n1. Matrikelnummer");
		System.out.println("2. Name");
		System.out.println("3. Adresse");
		System.out.println("4. Löschen");
		System.out.println("0. Zurück");
		switch (readLineConsole()) {
		case "1":
			System.out.println("\nBitte neue Matrikelnummer eingeben:");
			int matNr = Integer.valueOf(readLineConsole());
			st.setMatNr(matNr);
			tree.modify(node, genNode(st));
			//new
			tree.recursiveBalance(tree.getRoot());
			studentList.get(node.getValue()).setMatNr(matNr);
			break;
		case "2":
			System.out.println("\nBitte neuen Namen eingeben:");
			st.setName(readLineConsole());
			tree.modify(node, genNode(st));
			studentList.get(node.getValue()).setName(st.getName());
			break;
		case "3":
			System.out.println("\nBitte neue Adresse eingeben:");
			st.setAdresse(readLineConsole());
			tree.modify(node, genNode(st));
			studentList.get(node.getValue()).setAdresse(st.getAdresse());
			break;
		case "4":
			deleteStudent(st);
			break;
		case "0":
			break;
		default:
			modifyStudent(st);
			break;
		}
	}

	public static void printStudents() {
		ArrayList<BinaryTreeNode> inOrder = tree.inorder();
		Iterator<BinaryTreeNode> it;
		try {
			it = inOrder.iterator();
			int i = 1;
			while (it.hasNext()) {
				tmpSt = studentList.get(it.next().getValue());
				if (!tmpSt.isDeleted()) {
					i++;
					System.out.println("\n");
					System.out.println("\nStudent " + i);
					System.out.println(" Matrikelnummer: " + tmpSt.getMatNr());
					System.out.println(" Name:           " + tmpSt.getName());
					System.out.println(" Adresse:        " + tmpSt.getAdresse());
				}
			}
		} catch (NullPointerException e) {
			System.out.println("\n\nStudenteliste ist leer.\n\n");
		}
	}

	public static void printStudent(BinaryTreeNode node) {
		// System.out.println("\nStudent:");
		try {
			tmpSt = studentList.get(node.getValue());
			if (!tmpSt.isDeleted()) {
				System.out.println("\nMatrikelnummer: " + tmpSt.getMatNr());
				System.out.println("Name:           " + tmpSt.getName());
				System.out.println("Adresse:        " + tmpSt.getAdresse());
			} else {
				System.out.println("Matrikelnummer nicht vorhanden.");
			}
		} catch (NullPointerException e) {
			System.out.println("Matrikelnummer nicht vorhanden.");
		}
	}

	public static void printMenue() {
		System.out.println("\nMenü:");
		System.out.println("\n1. Student suchen");
		System.out.println("2. Student einfügen");
		System.out.println("3. Student löschen");
		System.out.println("4. Student bearbeiten");
		System.out.println("5. Studentenliste anzeigen");
		System.out.println("0. Programm beenden");
		tmpSt = new Student();
		int matNr;
		switch (readLineConsole()) {
		case "1":
			System.out.println("\nBitte Matrikelnummer des gesuchten Studenten eingeben:");
			matNr = Integer.valueOf(readLineConsole());
			BinaryTreeNode node = new BinaryTreeNode();
			node.setKey(matNr);
			node = tree.search(node);
			if (node == null) {
				System.out.println("\n\nMatrikelnummer " + matNr + " nicht vorhanden.\n\n");
			} else {
				printStudent(node);
				System.out.println("\nStudent bearbeiten? Y/N");
				if (readLineConsole().toLowerCase().contains("y")) {
					modifyStudent(studentList.get(node.getValue()));
				}
			}
			break;
		case "2":
			System.out.println("\nBitte Matrikelnummer des Studenten eingeben:");
			matNr = Integer.valueOf(readLineConsole());
			tmpSt.setMatNr(matNr);
			System.out.println("\nBitte Name des Studenten eingeben:");
			tmpSt.setName(readLineConsole());
			System.out.println("\nBitte Adresse des Studenten eingeben:");
			tmpSt.setAdresse(readLineConsole());
			addStudent(tmpSt);
			break;
		case "3":
			System.out.println("\nBitte Matrikelnummer des zu löschenden Studenten eingeben:");
			matNr = Integer.valueOf(readLineConsole());
			tmpSt.setMatNr(matNr);
			deleteStudent(tmpSt);
			break;
		case "4":
			System.out.println("\nBitte Matrikelnummer des zu bearbeitenden Studenten eingeben:");
			matNr = Integer.valueOf(readLineConsole());
			tmpSt.setMatNr(matNr);
			modifyStudent(tmpSt);
			break;
		case "5":
			printStudents();
			break;
		case "0":
			System.exit(1);
		default:
			printMenue();
			break;
		}
	}

	public static String readLineConsole() {
		ConsoleReader cr = new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
		try {
			return cr.readNextLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
