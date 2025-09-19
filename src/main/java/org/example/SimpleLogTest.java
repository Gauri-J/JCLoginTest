package org.example;

    import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

    public class SimpleLogTest {
        private static final Logger logger = LogManager.getLogger(SimpleLogTest.class);

        public static void main(String[] args) {
            logger.info("✅ Info log should appear");
            logger.debug("🐞 Debug log should appear");
            logger.error("❌ Error log should appear");
            System.out.println("📌 System.out.println also works");
        }
    }

