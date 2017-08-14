#Uses python3

import sys

def reach(adj, x, y):
    #write your code here
    v = explore(adj, x)
    return 1 if y in v else 0

def explore(adj, x):
    visited = set()
    explore_helper(adj, x, visited)
    return visited

def explore_helper(adj, x, v):
    v.add(x)
    for i in range(len(adj[x])):
        if adj[x][i] not in v:
            explore_helper(adj, adj[x][i], v)


if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    x, y = data[2 * m:]
    adj = [[] for _ in range(n)]
    x, y = x - 1, y - 1
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
        adj[b - 1].append(a - 1)
    print(reach(adj, x, y))
