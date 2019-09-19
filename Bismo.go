package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Loker struct {
	indek int
	no    int64
	tipe  string
}

var perintah string = ""
var lokers []Loker

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	fmt.Println("Loker's Spelling")
	fmt.Println("----------------")
	for strings.Compare(perintah, "EXIT") != 0 {
		fmt.Print("--> ")
		if scanner.Scan() {
			perintah = scanner.Text()
			eksekusi()
		}
	}
}

func eksekusi() {
	perintah = strings.ToUpper(perintah)
	if strings.Compare(perintah, "EXIT") == 0 {
		fmt.Println("Good bye :-)")
	} else if lokers == nil && strings.HasPrefix(perintah, "INIT ") && 2 == bagiKata() {
		initLokers()
	} else if lokers != nil && 0 == strings.Compare(perintah, "STATUS") {
		statusLokers()
	} else if lokers != nil && strings.HasPrefix(perintah, "INPUT ") && 3 == bagiKata() {
		inputLokers()
	} else if lokers != nil && strings.HasPrefix(perintah, "LEAVE ") && 2 == bagiKata() {
		leaveLokers()
	} else if lokers != nil && strings.HasPrefix(perintah, "FIND ") && 2 == bagiKata() {
		findLokers()
	} else if lokers != nil && strings.HasPrefix(perintah, "SEARCH ") && 2 == bagiKata() {
		srcLokers()
	} else {
		fmt.Println("Sorry, this spell not found :-(")
	}
}

func srcLokers() {
	var pecah = strings.Split(perintah, " ")
	var lanjut bool = false
	for i := 0; i < len(lokers); i++ {
		var l = lokers[i]
		if strings.Compare(pecah[1], l.tipe) == 0 {
			if lanjut {
				fmt.Print(", ")
			}
			fmt.Printf("%d", l.no)
			lanjut = true
		}
	}
	if lanjut {
		fmt.Println("")
	} else {
		fmt.Println("Tipe kartu ini tidak tersimpan")
	}
}

func findLokers() {
	var pecah = strings.Split(perintah, " ")
	var g, err = strconv.ParseInt(pecah[1], 0, 64)
	if err == nil && g > 0 {
		var ketemu bool = false
		for i, l := range lokers {
			if g == l.no {
				ketemu = true
				fmt.Printf("Kartu identitas tersebut berada di loker nomor %d\n", i+1)
			}
		}
		if !ketemu {
			fmt.Println("Kartu identitas tidak ditemukan")
		}
	} else {
		fmt.Println("Sorry, spell params cannot be allowed :-(")
	}
}

func leaveLokers() {
	var pecah = strings.Split(perintah, " ")
	var g, err = strconv.Atoi(pecah[1])
	if err == nil && g > 0 {
		if -1 != lokers[g-1].no && lokers[g-1].indek == g {
			lokers[g-1].no = -1
			lokers[g-1].tipe = "___"
			fmt.Printf("Loker nomor %d berhasil dikosongkan\n", g)
		} else {
			fmt.Printf("Loker nomor %d tidak berisi\n", g)
		}
	} else {
		fmt.Println("Sorry, spell params cannot be allowed :-(")
	}
}

func inputLokers() {
	var pecah = strings.Split(perintah, " ")
	var g, err = strconv.ParseInt(pecah[2], 0, 64)
	if err == nil && g > 0 {
		var terisi bool = false
		for i := 0; i < len(lokers); i++ {
			if lokers[i].no == -1 {
				lokers[i].no = g
				terisi = true
				lokers[i].tipe = pecah[1]
				fmt.Printf("Kartu identitas tersimpan di loker nomor %d\n", lokers[i].indek)
				break
			}
		}
		if !terisi {
			fmt.Println("Maaf loker sudah penuh")
		}
	} else {
		fmt.Println("Sorry, spell params cannot be allowed :-(")
	}
}

func statusLokers() {
	fmt.Println("No Loker\tTipe Identitas\tNo Identitas")
	for i := 0; i < len(lokers); i++ {
		l := lokers[i]
		fmt.Printf("%d\t\t%s\t\t%d\n", l.indek, l.tipe, l.no)
	}
}

func initLokers() {
	var pecah = strings.Split(perintah, " ")
	var g, err = strconv.Atoi(pecah[1])
	if err == nil && g > 0 {
		lokers = make([]Loker, g)
		for i := 0; i < g; i++ {
			lokers[i].indek = i + 1
			lokers[i].no = -1
			lokers[i].tipe = "___"
		}
		fmt.Printf("Berhasil membuat loker dengan jumlah %d\n", g)
	} else {
		fmt.Println("Sorry, spell params cannot be allowed :-(")
	}
}

func bagiKata() int {
	var pecah = strings.Split(perintah, " ")
	return len(pecah)
}
