package main

import "fmt"

func main() {
	g, a, b := getGraphAndPoints()
	v := dfs(g, a)
	if _, ok := v[b-1]; ok {
		fmt.Println(1)
	} else {
		fmt.Println(0)
	}
}

func dfs(g map[int][]int, x int) map[int]int {
	visited := make(map[int]int)
	exploreHelper(g, x, visited)
	return visited
}

func exploreHelper(g map[int][]int, x int, visited map[int]int) {
	visited[x] = 1
	for i := 0; i < len(g[x]); i++ {
		if _, ok := visited[g[x][i]]; !ok {
			exploreHelper(g, g[x][i], visited)
		}
	}
}

func getGraphAndPoints() (map[int][]int, int, int) {

	g := make(map[int][]int)
	var v, e int
	fmt.Scan(&v)
	fmt.Scan(&e)

	for i := 0; i < v; i++ {
		g[i] = []int{}
	}
	edges := e
	for i := 0; i < edges; i++ {
		fmt.Scan(&v)
		fmt.Scan(&e)
		g[v-1] = append(g[v-1], e-1)
		g[e-1] = append(g[e-1], v-1)
	}
	var a, b int
	fmt.Scan(&a)
	fmt.Scan(&b)
	return g, a, b
}
