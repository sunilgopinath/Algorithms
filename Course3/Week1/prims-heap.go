// This example demonstrates a weight queue built using the heap interface.
package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strconv"
	"strings"
)

// This example creates a PriorityQueue with some vertices, adds and manipulates a vertex,
// and then removes the vertices in weight order.
func main() {

	if len(os.Args) < 2 {
		fmt.Println("Missing parameter, provide file name!")
		return
	}
	path := os.Args[1]
	file, _ := os.Open(path)
	defer file.Close()

	input := createGraphFromFile(file)
	vertices := 500
	pq := make(PriorityQueue, vertices)
	for i := 0; i < vertices; i++ {
		pq[i] = &Vertex{
			label:  i + 1,
			weight: 2147483647, // meant to represent highest int
			index:  i,
		}
	}
	heap.Init(&pq)

	MST := make(map[int]int, 500)
	var totalCost int
	// pick node 1 (arbitrary)

	// update heap
	pq.decreaseKey(1, 0)

	for pq.Len() > 0 {
		// extract min
		v := heap.Pop(&pq).(*Vertex)
		// add to MST
		MST[v.label] = v.weight
		totalCost += v.weight
		// update neighbor values
		for _, elem := range input[v.label] {
			var vertex *Vertex
			vertex = &elem
			if _, ok := MST[vertex.label]; !ok {
				pq.decreaseKey(vertex.label, vertex.weight)
			}
		}
	}
	fmt.Println(totalCost)

}

//Vertex is something we manage in a priority queue.
type Vertex struct {
	label  int // The vertex label.
	weight int // The priority/weight of the node in the queue.
	// The index is needed by update and is maintained by the heap.Interface methods.
	index int // The index of the vertex in the heap.
}

// A PriorityQueue implements heap.Interface and holds Vertices.
type PriorityQueue []*Vertex

func (pq PriorityQueue) Len() int { return len(pq) }

func (pq PriorityQueue) Less(i, j int) bool {
	// We want Pop to give us the min weight.
	return pq[i].weight < pq[j].weight
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

//Push adds a Vertex to the heap
func (pq *PriorityQueue) Push(x interface{}) {
	n := len(*pq)
	vertex := x.(*Vertex)
	vertex.index = n
	*pq = append(*pq, vertex)
}

//Pop extracts the min element from the heap
func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	vertex := old[n-1]
	vertex.index = -1 // for safety
	*pq = old[0 : n-1]
	return vertex
}

// find better way of finding and updating, use better binary heap search
func (pq *PriorityQueue) decreaseKey(label int, weight int) {
	old := *pq
	for i := 0; i < len(old); i++ {
		var vertex *Vertex
		vertex = old[i]
		if vertex.label == label {
			if vertex.weight > weight {
				vertex.weight = weight
				heap.Fix(pq, vertex.index)
			}
			break
		}
	}
}

func createGraphFromFile(file *os.File) map[int][]Vertex {
	graph := make(map[int][]Vertex)

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := strings.Split(scanner.Text(), " ")
		key, _ := strconv.Atoi(line[0])
		l, _ := strconv.Atoi(line[1])
		w, _ := strconv.Atoi(line[2])
		v := Vertex{
			label:  l,
			weight: w,
		}
		v1 := Vertex{
			label:  key,
			weight: w,
		}
		graph[key] = append(graph[key], v)
		graph[l] = append(graph[l], v1)
	}
	return graph
}
