# Node.js image as base
FROM node:20-bullseye AS builder

# Establish project directory inside container
WORKDIR /app

# Copy JSON files to install dependencies
COPY package.json package-lock.json ./

# Deletes package-lock.json and node_modules before installing dependencies
RUN rm -rf package-lock.json node_modules

# Install dependencies
RUN npm install

# 6️⃣ Manually install rollup to avoid errors with optional dependencies
RUN npm install --force @rollup/rollup-linux-x64-gnu

# Copy source code from frontend to container
COPY . .

# Compile project
RUN npm run build

# Use Nginx image for efficient application performance
FROM nginx:alpine

# Copy files created in `dist/` to Nginx directory
COPY --from=builder /app/dist /usr/share/nginx/html

# Expose 80 port to serve the application
EXPOSE 80

# Run Nginx
CMD ["nginx", "-g", "daemon off;"]
