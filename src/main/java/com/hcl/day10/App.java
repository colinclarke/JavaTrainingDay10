package com.hcl.day10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App {
	public static void main(String[] args) {
//		System.out.println(">> 1. Echo back what you type");
//		echo();

		System.out.println("\n>> 2. Write a message to file");
		makeFile("testing.txt", "This message will be written to the file!");

		System.out.println("\n>> 3. Print contents of file");
		printFile("testing.txt");

		System.out.println("\n>> 4. Write text from console and read it from file");
		writeAndReadFile("writeandread.txt");

		System.out.println("\n>> 5. Write a serializable object to a file, then deserialize it");
		writeAndReadObject();
	}

	public static void echo() {
		String string;
		System.out.println("Echos back what you type. Type \"stop\" to exit");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			do {
				string = br.readLine();
				System.out.println(string);
			} while (!string.equals("stop"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static void makeFile(String filename, String message) {
		try (FileWriter writer = new FileWriter(filename);) {
			writer.write(message);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printFile(String filename) {
		String string;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename));) {
			while ((string = reader.readLine()) != null) {
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeAndReadFile(String filename) {
		String string;
		try (FileWriter writer = new FileWriter(filename);
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			System.out.println("Enter what you want to write to the file. Type ctrl + z when done.");
			while (true) {
				string = br.readLine();
				if (string != null) {
					writer.write(string + "\n");
					writer.flush();
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("You wrote to the file:");
		try (BufferedReader reader = new BufferedReader(new FileReader(filename));) {
			while ((string = reader.readLine()) != null) {
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			// Nothing written to file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeAndReadObject() {
		NetworkInfo obj = new NetworkInfo("127.0.0.1", 1092834, 80);
		NetworkInfo deserializedObj;
		System.out.println(obj);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialized.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialized.txt"))) {
			oos.writeObject(obj);
			printFile("serialized.txt");
			deserializedObj = (NetworkInfo) ois.readObject();
			System.out.println(deserializedObj);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
