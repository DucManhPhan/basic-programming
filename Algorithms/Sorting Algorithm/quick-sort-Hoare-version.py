# Hoare version
def parition(arr, left, right):
    i = left
    j = right
    middle = int((left + right) / 2)
    pivot = arr[middle]

    while True:
        while arr[i] < pivot:
            i += 1

        while arr[j] > pivot:
            j -= 1

        if i >= j:
            return j

        arr[i], arr[j] = arr[j], arr[i]


def quick_sort(arr, left, right):
    if left >= right:
        return

    pivot = parition(arr, left, right)

    quick_sort(arr, left, pivot)
    quick_sort(arr, pivot + 1, right)


if __name__ == "__main__":
    arr = [5, 3, 6, 8, 1, 2, 10]

    left = 0
    right = len(arr) - 1

    quick_sort(arr, left, right)

    print(arr)