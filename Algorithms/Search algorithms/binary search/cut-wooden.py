# Có N cây gỗ, có chiều cao lần lượt là A[1],A[2],..,A[n]. 
# Bạn cần lấy một lượng gỗ độ cao tối thiểu là M bằng cách chặt từ N cây theo cách như sau: 
# chặt tất cả những phần thừa của các cây có độ cao lớn hơn H. 
# Hãy tìm giá trị H lớn nhất để bạn có thể lấy được lượng gỗ tối thiểu là M.

# Input:
# Dòng 1 chứa 2 số nguyên N (1<=N<=1 000 000) và M (1 <= M <= 2 000 000 000).
# Dòng 2 chứa N số nguyên A[1],A[2],…,A[n], là chiều cao mỗi cây gỗ tương ứng (A[i] <= 1 000 000 000, i=1->n). Giả sử luôn tồn tại cách chặt.
# Output
# Số H duy nhất.

# Input:
# 4 7
# 20 15 10 17
# Output:
# 15

# Giải thích:
# Cây 1 chặt được (20-15)=5.
# Cây 4 chặt được (17-15)=2.
# Tổng số gỗ chặt được nếu H=15 là 7.

# Input:
# 5 20
# 4 42 40 26 46
# Output
# 36

import random

def quicksort(array):
    if len(array) <= 1:
        return array

    return quicksort([x for x in array[1:] if x < array[0]]) \
        + [array[0]]                                         \
        + quicksort([x for x in array[1:] if x >= array[0]])

def solution(array, M):
    size = len(array)

    # sort array by ascending order
    # array = quicksort(array)
    T = sum(array)

    # binary search
    # array[0] = 0
    S = 0
    H = 0
    for i in range(1, size - 1):
        if (T - S <= M):
            K = int((T - S - M) / (size - i))
            H = K + array[i - 1]
            break

        S += (array[i] - array[i - 1]) * (size - i)

    return H


if __name__ == "__main__":
    arr = [4, 42, 40, 26, 46] #[20, 15, 10, 17]
    M = 20

    arr.insert(0, 0)
    arr = quicksort(arr)

    # solve solution
    result_H = solution(arr, M)

    print('The result H of this solution is: ' + str(result_H))
