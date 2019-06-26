def perform_binary_search(array, left, right, key):
    if left > right:
        return -1

    mid = int(left + ((right - left) / 2))
    if array[mid] == key:
        return mid
    elif array[mid] > key:
        return perform_binary_search(array, left, mid - 1, key)
    else:
        return perform_binary_search(array, mid + 1, right, key)


def binary_search(array, key):
    size = len(array)
    left = 0
    right = size - 1

    return perform_binary_search(array, left, right, key)


if __name__ == "__main__":
    lst = [1, 3, 5, 8, 9, 10]
    key = 12

    index_of_key = binary_search(lst, key)

    if index_of_key != -1:
        print('The index of this key is: ' + str(index_of_key))
    else:
        print('Do not find this key.')
