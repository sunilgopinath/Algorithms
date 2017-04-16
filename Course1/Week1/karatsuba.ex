# karatssuba.ex
defmodule Karatsuba do
  def calculate_product(x,y) when x <= 10 or y <=10 do
    x * y
  end

  def calculate_product(x,y) do
    # formula is a*r^n + e*r^n/2 + d
    # where a1 = a * c
    # where d1 = b * d
    # where n = length of input
    # where r is base of number (usually 10)
    # let a = first half of x
    # let b = second half of y
    # let c = first half of y
    # let d = second half of y
    # where e  = (a + b) * (c + d) - a1 - d1
    # result = a * 10^n + e * 10^n/2 + d
    x0 = Integer.digits(x)
    y0 = Integer.digits(y)
    n = x0 |> length
    a = x0 |> split_first(n)
    b = x0 |> split_second(n)
    c = y0 |> split_first(n)
    d = y0 |> split_second(n)
    a1 = calculate_product(a,c)
    d1 = calculate_product(b,d)
    e1 = calculate_product((a+b),(c+d)) - a1 - d1
    a1 * :math.pow(10,n) + e1 * :math.pow(10,(div(n,2))) + d1
  end

  defp split_first(digits, len) do
    digits
    |> Enum.take(div(len,2)) |> Enum.join |> String.to_integer
  end

  defp split_second(digits, len) do
    digits
    |> Enum.take(-div(len,2)) |> Enum.join |> String.to_integer
  end
end
