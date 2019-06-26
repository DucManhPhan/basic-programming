def quicksort(array):
    if len(array) <= 1:
        return array

    return quicksort([x for x in array[1:] if x < array[0]]) \
        + [array[0]]                                         \
        + quicksort([x for x in array[1:] if x >= array[0]])

if __name__ == "__main__":
    lst = [3, 4, 1, 2, 5, 9, 8, 3]
    lst = quicksort(lst)

    print(lst)  # [1, 2, 3, 3, 4, 5, 8, 9]
