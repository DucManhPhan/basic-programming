# Given a sorted array of integers, find the number of occurrences of a given target value.
# Your algorithm’s runtime complexity must be in the order of O(log n).
# If the target is not found in the array, return 0

# **Example : **
# Given [5, 7, 7, 8, 8, 10] and target value 8,
# return 2.

def get_position(array, key, isFoundLeftSide):
    left = 0
    right = len(array) - 1
    position = 0

    while left <= right:
        mid = int(left + (right - left) / 2)

        if array[mid] == key:
            position = mid
            if isFoundLeftSide == True:
                right = mid - 1
            else:
                left = mid + 1

        elif array[mid] > key:
            right = mid - 1
        else:
            left = mid + 1

    return position


def count(array, key):
    start_position = get_position(array, key, True)     # O(logn)
    end_position = get_position(array, key, False)      # O(logn)

    return end_position - start_position + 1

if __name__ == "__main__":
    array = [5, 7, 7, 8, 8, 10]
    key = 10

    num = count(array, key)

    print('The number of occurence of key is: ' + str(num))