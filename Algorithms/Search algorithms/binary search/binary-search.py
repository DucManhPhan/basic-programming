

def binary_search(array, item):
    upper = len(array) - 1
    lower = 0
    found = False

    while (lower <= upper) and not found:
        mid = (upper + lower) // 2
        if array[mid] == item:
            found = True
        else:
            if array[mid] > item:
                upper = mid - 1
            else:
                lower = mid + 1
    
    return found

arr = [1, 2, 3, 4, 5, 6, 7, 9]
item = 5

if __name__ == "__main__":
    isFound = binary_search(arr, item)
    if isFound:
        print("Found.\n")
    else:
        print("Not found.\n")

