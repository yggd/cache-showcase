import ch.qos.logback.classic.Level
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = '%d{HH:mm:ss.SSS}[%thread][%-5level]<%logger{10}> - %message%n'
    }
}
root Level.INFO, ["CONSOLE"]
