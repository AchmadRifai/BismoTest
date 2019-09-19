
import java.util.List;
import java.util.Scanner;

public class Bismo {
	private static String perintah = "";
	private static List<Bismo.Loker> lokers = new java.util.LinkedList<Bismo.Loker>();

	public static void main(String[] args) {
		Scanner i = new Scanner(System.in);
		System.out.println("Loker's Spelling");
		System.out.println("----------------");
		while (!perintah.equals("EXIT")) {
			System.out.print("--> ");
			perintah = i.nextLine();
			eksekusi();
		} i.close();
	}

	private static void eksekusi() {
		perintah = perintah.toUpperCase();
		if (perintah.equals("EXIT")) {
			System.out.println("Good bye! :-)");
		} else if (0 == lokers.size() && perintah.startsWith("INIT ") && 2 == bagiKata()) {
			initLokers();
		} else if (0 < lokers.size() && perintah.equals("STATUS")) {
			statusLokers();
		} else if (0 < lokers.size() && perintah.startsWith("INPUT ") && 3 == bagiKata()) {
			inputLokers();
		} else if (0 < lokers.size() && perintah.startsWith("LEAVE ") && 2 == bagiKata()) {
			leaveLokers();
		} else if (0 < lokers.size() && perintah.startsWith("FIND ") && 2 == bagiKata()) {
			findLokers();
		} else if (0 < lokers.size() && perintah.startsWith("SEARCH ") && 2 == bagiKata()) {
			srcLokers();
		} else System.out.println("Spell cannot be realize :-(");
	}

	private static void srcLokers() {
		String[] pecah = perintah.split(" ");
		boolean lanjut = false, kosong = true;
		for (Bismo.Loker l:lokers) if(pecah[1].equals(l.tipe)) {
			if (lanjut) System.out.print(", ");
			System.out.print(l.no);
			lanjut = true;
			kosong = false;
		} if (lanjut) System.out.println();
		if (kosong) System.out.println("Tipe kartu ini tidak tersimpan");
	}

	private static void findLokers() {
		String[] pecah = perintah.split(" ");
		try {
			long g = Long.parseLong(pecah[1]);
			boolean ketemu = false;
			for (Bismo.Loker l:lokers) if (g == l.no) {
				ketemu = true;
				System.out.println("Kartu identitas tersebut berada di loker nomor " + l.indek);
			} if (!ketemu) System.out.println("Kartu identitas tidak ditemukan");
		} catch (NumberFormatException e) {
			System.out.println("Sorry, spell params cannot be allowed :-(");
		}
	}

	private static void leaveLokers() {
		String[] pecah = perintah.split(" ");
		try {
			int g = Integer.parseInt(pecah[1]);
			Bismo.Loker l = lokers.get(g - 1);
			if (-1 < l.no && g == l.indek) {
				l.no = -1;
				l.tipe = "___";
				System.out.println("Loker nomor " + g + " berhasil dikosongkan");
			} else System.out.println("Loker nomor " + g + " tidak berisi");
		} catch (NumberFormatException e) {
			System.out.println("Sorry, spell params cannot be allowed :-(");
		}
	}

	private static void inputLokers() {
		String[] pecah = perintah.split(" ");
		try {
			long g = Long.parseLong(pecah[2]);
			boolean terisi = false;
			for (int i = 0; i < lokers.size(); i++) {
				Bismo.Loker l = lokers.get(i);
				if (l.no == -1) {
					l.no = g;
					l.tipe = pecah[1];
					terisi = true;
					System.out.println("Kartu identitas tersimpan di loker nomor " + l.indek);
					break;
				}
			} if (!terisi) System.out.println("Maaf loker sudah penuh");
		} catch (NumberFormatException e) {
			System.out.println("Sorry, spell params cannot be allowed :-(");
		}
	}

	private static void statusLokers() {
		System.out.println("No Loker\tTipe Identitas\tNo Identitas");
		for (Bismo.Loker l:lokers) System.out.println(l);
	}

	private static void initLokers() {
		String[] pecah = perintah.split(" ");
		try {
			int g = Integer.parseInt(pecah[1]);
			if (g > 0) {
				for (int i = 0; i < g; i++) lokers.add(new Bismo.Loker(i + 1, "___", -1));
				System.out.println("Berhasil membuat loker dengan jumlah " + g);
			} else System.out.println("Sorry, spell params cannot be allowed :-(");
		} catch(NumberFormatException e) {
			System.out.println("Sorry, spell params cannot be allowed :-(");
		}
	}

	private static int bagiKata() {
		String[] pecah = perintah.split(" ");
		return pecah.length;
	}

	public static class Loker {
		private int indek;
		private String tipe;
		private long no;

		public Loker() {
			super();
		}

		public Loker(int indek, String tipe, long no) {
			super();
			this.indek = indek;
			this.tipe = tipe;
			this.no = no;
		}

		public int getIndek() {
			return indek;
		}

		public void setIndek(int indek) {
			this.indek = indek;
		}

		public String getTipe() {
			return tipe;
		}

		public void setTipe(String tipe) {
			this.tipe = tipe;
		}

		public long getNo() {
			return no;
		}

		public void setNo(long no) {
			this.no = no;
		}

		@Override
		public String toString() {
			return "" + indek + "\t\t" + tipe + "\t\t" + no;
		}
	}
}
