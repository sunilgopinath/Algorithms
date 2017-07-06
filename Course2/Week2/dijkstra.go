package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

//Node represents a node of the graph
type Node struct {
	Name   int
	Weight int
}

func main() {

	if len(os.Args) < 2 {
		fmt.Println("Missing parameter, provide file name!")
		return
	}
	path := os.Args[1]
	file, _ := os.Open(path)
	defer file.Close()

	input := createGraphFromFile(file)
	heapMap := initializeWeights(1, len(input))
	A := dijkstra(input, heapMap)

	qs := []int{7, 37, 59, 82, 99, 115, 133, 165, 188, 197}
	for _, element := range qs {
		fmt.Print(A[element], ",")
	}
}

func createGraphFromFile(file *os.File) map[int][]Node {
	graph := make(map[int][]Node)

	scanner := bufio.NewScanner(file)
	var key int
	for scanner.Scan() {
		line := strings.Split(scanner.Text(), "\t")
		for x := range line {
			if line[x] != "" {
				if x == 0 {
					key, _ = strconv.Atoi(line[x])
				} else {
					node := strings.Split(line[x], ",")
					n, _ := strconv.Atoi(node[0])
					w, _ := strconv.Atoi(node[1])
					n1 := Node{
						Name:   n,
						Weight: w,
					}
					graph[key] = append(graph[key], n1)
				}
			}
		}
	}
	return graph
}

func initializeWeights(sourceVertex int, n int) map[int]int {
	m := make(map[int]int)
	for i := 1; i <= n; i++ {
		m[i] = 2147483647 // meant to represent highest int
	}
	m[sourceVertex] = 0
	return m
}

func dijkstra(input map[int][]Node, heapMap map[int]int) map[int]int {

	A := make(map[int]int)
	// main while loop
	for len(heapMap) > 0 {
		//1. Find the smallest distance
		min := getMinimalDistance(heapMap).Pop()

		//2. Add to finalized Set
		A[min.Name] = min.Weight

		//3. Remove min from map
		delete(heapMap, min.Name)

		//4. Explore neighbours of min
		neighbors := input[min.Name]
		for _, element := range neighbors {
			if _, ok := heapMap[element.Name]; !ok {
				continue
			} else if A[min.Name]+element.Weight < heapMap[element.Name] {
				heapMap[element.Name] = A[min.Name] + element.Weight
			}
		}
	}
	return A
}

func getMinimalDistance(graph map[int]int) NodeList {
	nl := make(NodeList, len(graph))
	i := 0
	for k, v := range graph {
		nl[i] = Node{k, v}
		i++
	}
	sort.Sort(sort.Reverse(nl))
	return nl
}

//NodeList is a list of nodes
type NodeList []Node

func (n NodeList) Len() int           { return len(n) }
func (n NodeList) Less(i, j int) bool { return n[i].Weight > n[j].Weight }
func (n NodeList) Swap(i, j int)      { n[i], n[j] = n[j], n[i] }

//Pop returns the smallest element of the data structure
func (n NodeList) Pop() Node { return n[0] }
