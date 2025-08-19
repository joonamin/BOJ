def solution(numbers):
    result = 0
    for i in range(0, len(numbers)):
        for j in range(i + 1, len(numbers)):
            result = max(result, numbers[i] * numbers[j])
    return result