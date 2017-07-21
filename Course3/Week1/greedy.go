package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

// DifferenceSorter sorts planets by axis.
type DifferenceSorter []Job

func (d DifferenceSorter) Len() int      { return len(d) }
func (d DifferenceSorter) Swap(i, j int) { d[i], d[j] = d[j], d[i] }
func (d DifferenceSorter) Less(i, j int) bool {
	if d[i].Difference == d[j].Difference {
		return d[i].Weight > d[j].Weight
	}
	return d[i].Difference > d[j].Difference
}

// RatioSorter sorts planets by axis.
type RatioSorter []Job

func (r RatioSorter) Len() int      { return len(r) }
func (r RatioSorter) Swap(i, j int) { r[i], r[j] = r[j], r[i] }
func (r RatioSorter) Less(i, j int) bool {
	return r[i].Ratio > r[j].Ratio
}

//Job represents a job
type Job struct {
	Weight     int
	Length     int
	Difference int
	Ratio      float64
}

func main() {

	if len(os.Args) < 2 {
		fmt.Println("Missing parameter, provide file name!")
		return
	}
	path := os.Args[1]
	file, _ := os.Open(path)
	defer file.Close()

	var jobs []Job
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		var j Job
		s := strings.Split(scanner.Text(), " ")
		w, _ := strconv.Atoi(s[0])
		l, _ := strconv.Atoi(s[1])

		j.Weight = w
		j.Length = l
		j.Difference = w - l
		j.Ratio = float64(w) / float64(l)
		jobs = append(jobs, j)
	}

	sort.Sort(DifferenceSorter(jobs))
	var c, sum int

	for _, j := range jobs {
		c = c + j.Length
		sum = sum + j.Weight*c
	}
	fmt.Println(sum)

	sort.Sort(RatioSorter(jobs))
	var rc, rsum int

	for _, j := range jobs {
		rc = rc + j.Length
		rsum = rsum + j.Weight*rc
		// fmt.Println(j.Weight, j.Difference)
	}
	fmt.Println(rsum)
}
