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
    n = Integer.digits(x) |> length
    a = split_first(x, div(n, 2))
    b = split_second(x, div(n, 2))
    c = split_first(y, div(n, 2))
    d = split_second(y, div(n, 2))
    a1 = calculate_product(a,c)
    d1 = calculate_product(b,d)
    e1 = calculate_product((a+b),(c+d)) - a1 - d1
    a1 * :math.pow(10,n) + e1 * :math.pow(10,(div(n,2))) + d1
  end

  defp split_first(x, len) do
    div(x, round(:math.pow(10, len)))

  end

  defp split_second(x, len) do
    rem(x, round(:math.pow(10, len)))
  end
end
