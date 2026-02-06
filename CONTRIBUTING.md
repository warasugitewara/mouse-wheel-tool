# Contributing

Thank you for your interest in contributing to Mouse Wheel Tool!

## How to Contribute

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/your-feature`)
3. **Commit** your changes (`git commit -am 'Add your feature'`)
4. **Push** to the branch (`git push origin feature/your-feature`)
5. **Open** a Pull Request

## Development Setup

### Prerequisites
- Java 21+
- Maven 3.6+

### Build
```bash
mvn clean package
```

### Run
```bash
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

## Testing

### Mouse Wheel Test
```bash
mvn test-compile
java -cp "target/classes:target/test-classes" mousewheeltool.MouseWheelTest
```

## Code Style

- Follow Java naming conventions
- Use 4 spaces for indentation
- Add comments for complex logic
- Keep methods focused and concise

## Reporting Issues

When reporting issues, please include:
- Operating System (Windows/Linux/macOS)
- Java version
- Detailed steps to reproduce
- Expected vs actual behavior
- Error messages/logs if available

## Feature Requests

Feature requests are welcome! Please describe:
- Use case
- Expected behavior
- Why it would be useful

## License

All contributions are licensed under the MIT License.
