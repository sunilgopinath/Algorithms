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
	_, b := sortAndCount(lines)
	fmt.Println(b)
}

func sortAndCount(a []int) ([]int, int) {
	if len(a) == 1 {
		return a, 0
	}
	first := a[:len(a)/2]
	second := a[len(a)/2:]
	b, x := sortAndCount(first)
	c, y := sortAndCount(second)
	d, z := mergeAndCountSplitInversions(b, c)
	return d, x + y + z
}

func mergeAndCountSplitInversions(b []int, c []int) ([]int, int) {

	var splitInversions = 0
	var i = 0
	var j = 0
	n := len(b) + len(c)
	d := make([]int, n)
	for k := 0; k < n; k++ {
		if j == len(c) {
			d[k] = b[i]
			i++
		} else if i == len(b) {
			d[k] = c[j]
			j++
		} else {
			if b[i] < c[j] {
				d[k] = b[i]
				i++
			} else if c[j] < b[i] {
				d[k] = c[j]
				splitInversions = splitInversions + (len(b) - i)
				j++
			}
		}
	}
	return d, splitInversions
}
