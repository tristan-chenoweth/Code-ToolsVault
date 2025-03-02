# Define test cases with expected outputs
$testCases = @(
    @{ input = "a`n5`n3`nq"; expected = "Result: 8" }
    @{ input = "s`n10`n4`nq"; expected = "Result: 6" }
    @{ input = "m`n6`n7`nq"; expected = "Result: 42" }
    @{ input = "d`n9`n3`nq"; expected = "Result: 3.0" }
    @{ input = "d`n8`n0`nq"; expected = "Error: Division by zero" }
    @{ input = "e`n2`n3`nq"; expected = "Result: 8" }
    @{ input = "o`n10`n3`nq"; expected = "Result: 1" }
    @{ input = "f`n5`nq"; expected = "Result: 120" }
    @{ input = "x`n19`n4`nq"; expected = "Invalid operator" }
)

# Path to Ruby script
$rubyScriptPath = "C:/Users/chenowt/Documents/ObsidianVault/NewGaiaVault/theMotherVault/NestedVaults/Code-ToolsVault/CodeLanguages/Ruby/Calculator/calculator.rb"

foreach ($test in $testCases) {
    Write-Host "`nRunning test with input:`n$($test.input)"

    # Capture output from Ruby script
    $actualOutput = $test.input | ruby $rubyScriptPath 2>&1

    Start-Sleep -Milliseconds 500

    # Find the line containing the result
    $filteredOutput = $actualOutput | Select-String -Pattern "Result:|Error:|Invalid|Restarting"

    if ($filteredOutput -match $test.expected) {
        Write-Host "Test Passed!`nExpected: '$($test.expected)'`nGot: '$filteredOutput'"
    } else {
        Write-Host "Test Failed!`nExpected: '$($test.expected)'`nActual:   '$filteredOutput'"
    }
    Start-Sleep -Milliseconds 500
}
