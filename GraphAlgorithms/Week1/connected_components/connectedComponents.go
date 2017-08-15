package main

import "fmt"

// Sample input graph representation
// 4 2
// 1 2
// 3 2

func main() {
	g := getGraph()
	fmt.Println(components(g))
}

func components(g map[int][]int) int {

	cc := 0
	visited := make(map[int]int)
	for k := range g {
		if _, ok := visited[k]; !ok {
			explore(g, k, visited)
			cc++
		}
	}
	return cc
}

func explore(g map[int][]int, x int, s map[int]int) {
	s[x] = 1
	for i := 0; i < len(g[x]); i++ {
		if _, ok := s[g[x][i]]; !ok {
			explore(g, g[x][i], s)
		}
	}
}

func getGraph() map[int][]int {
	// get the number of vertices and edges
	var v, e int
	fmt.Scan(&v)
	fmt.Scan(&e)

	// create a graph represented by an adjacency list
	g := make(map[int][]int)

	// initialize graph
	for i := 0; i < v; i++ {
		g[i] = []int{}
	}
	// read in input and load into graph
	for i := 0; i < e; i++ {
		fmt.Scan(&v)
		fmt.Scan(&e)
		g[v-1] = append(g[v-1], e-1)
		g[e-1] = append(g[e-1], v-1)
	}
	return g

}
