import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path' // <- Make sure to import path

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      __PUBLIC: path.resolve(__dirname, 'public'),
      __SRC: path.resolve(__dirname, 'src'),
      __FONTS: path.resolve(__dirname, 'public/fonts'), // <- Now points to the actual directory
      __CSS_ASSETS: path.resolve(__dirname, 'src/CSS/ASSET_IMPORT')
    }
  }
})