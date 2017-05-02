package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("Missing parameter, provide file name!")
		return
	}
	path := os.Args[1]
	file, _ := os.Open(path)
	defer file.Close()

	var lines []int
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		x, _ := strconv.Atoi(scanner.Text())
		lines = append(lines, x)
	}
	fmt.Println(quicksort(lines, 0, len(lines)-1))
}

func partition(a []int, l int, r int) int {
	// fmt.Println("partition a = ", a)
	p := a[l]
	// fmt.Println("partion p = ", p)
	i := l + 1
	for j := l + 1; j <= r; j++ {
		if a[j] < p {
			a[j], a[i] = a[i], a[j]
			i++
		}
	}
	a[l], a[i-1] = a[i-1], a[l]
	// fmt.Println("a = ", a)
	return (i - 1)
}

func quicksort(a []int, l int, r int) []int {
	// fmt.Println("quicksort a = ", a)
	if l < r {
		p := partition(a, l, r)
		quicksort(a, p+1, r)
		quicksort(a, l, p)
	}
	return a
}
