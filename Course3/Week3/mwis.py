WEIGHTED_GRAPH = "wis.txt"
inFile = open(WEIGHTED_GRAPH, 'r')

with inFile as f:
    numList = [int(integers.strip()) for integers in f.readlines()]

def find_wis(path_graph):
    A = [0, numList[0]]
    n = len(path_graph)
    for i in range(2, n+1):
        A.append(max(A[i-1], A[i-2] + path_graph[i-1]))

    indices = {}
    i = n
    while i >= 1:
        if(A[i-1] >= A[i-2] + path_graph[i-1]):
            i -= 1
        else:
            indices[i] = 1
            i -= 2

    return indices
    
m = find_wis(numList)
q = [1, 2, 3, 4, 17, 117, 517, 997]
for i in q:
    if i in m:
        print m[i]
    else:
        print '0'
